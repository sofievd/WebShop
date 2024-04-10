package se.iths.webshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import se.iths.webshop.entity.Order;
import se.iths.webshop.entity.OrderLine;
import se.iths.webshop.entity.Product;
import se.iths.webshop.repository.OrderLineRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineServiceTest {

    OrderLineService service;
    MockOrdelineRepo repo;

    @BeforeEach
    void setUp(){

    }

    @Test
    void createOrderLine() {

    }

    @Test
    void getOrderLinesByOrder() {

    }
    class MockOrdelineRepo implements OrderLineRepo{

        @Override
        public List<OrderLine> findByOrder(Order order) {
            List<OrderLine> list = new ArrayList<>();
            OrderLine line1 = new OrderLine(1, new Product("test1", 34.5), order,2);
            OrderLine line2 = new OrderLine(2, new Product("test2", 25.6), order,4);
            OrderLine line3 = new OrderLine(3, new Product("test3", 41.2), order,1);
            OrderLine line4 = new OrderLine(4, new Product("test4", 39.8), order,2);
            Collections.addAll(list, line1, line2, line3, line4);

            return list;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends OrderLine> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends OrderLine> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<OrderLine> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Integer> integers) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public OrderLine getOne(Integer integer) {
            return null;
        }

        @Override
        public OrderLine getById(Integer integer) {
            return null;
        }

        @Override
        public OrderLine getReferenceById(Integer integer) {
            return null;
        }

        @Override
        public <S extends OrderLine> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends OrderLine> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends OrderLine> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends OrderLine> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends OrderLine> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends OrderLine> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends OrderLine, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }

        @Override
        public <S extends OrderLine> S save(S entity) {
            return null;
        }

        @Override
        public <S extends OrderLine> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<OrderLine> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public List<OrderLine> findAll() {
            return null;
        }

        @Override
        public List<OrderLine> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(OrderLine entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends OrderLine> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public List<OrderLine> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<OrderLine> findAll(Pageable pageable) {
            return null;
        }
    }
}