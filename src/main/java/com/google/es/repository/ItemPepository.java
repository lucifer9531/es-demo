package com.google.es.repository;

import com.google.es.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ItemPepository extends ElasticsearchRepository<Item, Long> {

    List<Item> findByPriceBetween(Double begin, Double end);
}
