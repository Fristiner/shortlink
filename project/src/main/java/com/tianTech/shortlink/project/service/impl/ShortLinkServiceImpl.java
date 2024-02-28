package com.tianTech.shortlink.project.service.impl;
/*
 * {@code @author} ma
 * {@code @date} 2024-02-19 9:29
 * {@code @project} shortlink
 *
 */


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianTech.shortlink.project.common.convention.exception.ClientException;
import com.tianTech.shortlink.project.dao.entity.ShortLinkDO;
import com.tianTech.shortlink.project.dao.mapper.ShortLinkMapper;
import com.tianTech.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.tianTech.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.tianTech.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.tianTech.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.tianTech.shortlink.project.service.ShortLinkService;
import com.tianTech.shortlink.project.util.HashUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * {@code @projectName:}    shortlink
 * {@code @package:}        com.tianTech.shortlink.project.service.impl
 * {@code @className:}      ShortLinkServiceImpl
 * {@code @author:}         ma
 * {@code @date:}           2024-02-19 9:29
 * {@code @description:}
 */
@Service
@RequiredArgsConstructor
//@Slf4j
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    private final RBloomFilter<String> shortUrlCreatPenetrationBloomFilter;

    /**
     * 创建短链接
     *
     * @param requestParam
     * @return
     */
    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam);
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setShortUri(shortLinkSuffix);
        shortLinkDO.setEnableStatus(0);
        String fullShortUrl = requestParam.getDomain() + "/" + shortLinkSuffix;
        shortLinkDO.setFullShortUrl(fullShortUrl);
        try {
            baseMapper.insert(shortLinkDO);
        } catch (DuplicateKeyException e) {
            // TODO 已经误判的短链接如何处理
//            throw new RuntimeException(e);
            throw new ClientException("短链接重复入库");
        }
        shortUrlCreatPenetrationBloomFilter.add(fullShortUrl);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid()).build();
//        return null;
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getDelFlag, 0)
                .eq(ShortLinkDO::getEnableStatus, 0)
                .orderByDesc(ShortLinkDO::getCreateTime);
        IPage<ShortLinkDO> result = baseMapper.selectPage(requestParam, queryWrapper);
        return result.convert(each -> BeanUtil.toBean(each, ShortLinkPageRespDTO.class));
    }


    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        String shortUri = null;
        int customGenerateCount = 0;
        while (true) {
            if (customGenerateCount > 10) {
                throw new ClientException("短链接频繁生成请稍后再试");
            }
            String originUrl = requestParam.getOriginUrl();
//            originUrl += System.currentTimeMillis();
            shortUri = HashUtil.hashToBase62(originUrl);
            String fullShortUrl = requestParam.getDomain() + "/" + shortUri;
            boolean contains = shortUrlCreatPenetrationBloomFilter.contains(fullShortUrl);
            if (!contains) {
                break;
            }
            customGenerateCount++;
        }
        return shortUri;
    }


}
