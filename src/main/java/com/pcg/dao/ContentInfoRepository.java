package com.pcg.dao;

import com.pcg.entity.ContentInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @InferfaceName ContentInfoRepository
 * @AUTHOR 潘晨光
 * @DATE 2019/04/11 10:58
 **/
public interface ContentInfoRepository extends PagingAndSortingRepository < ContentInfo,Long> {
    List <ContentInfo> findByStatus ( int status );
}
