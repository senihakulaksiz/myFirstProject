package com.example.myFirstProject.service;

import com.example.myFirstProject.dto.OrderItemDetailDTO;
import com.example.myFirstProject.dto.OrderItemSummaryDTO;
import com.example.myFirstProject.model.Order;
import com.example.myFirstProject.model.OrderItem;
import com.example.myFirstProject.model.Product;
import com.example.myFirstProject.repository.OrderItemRepository;
import com.example.myFirstProject.repository.OrderRepository;
import com.example.myFirstProject.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository,
                                OrderRepository orderRepository,
                                ProductRepository productRepository,
                                ModelMapper modelMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<OrderItemSummaryDTO> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(orderItem -> {
                    OrderItemSummaryDTO dto = modelMapper.map(orderItem, OrderItemSummaryDTO.class);
                    dto.setItemName(orderItem.getProduct().getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDetailDTO getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .map(orderItem -> {
                    OrderItemDetailDTO dto = modelMapper.map(orderItem, OrderItemDetailDTO.class);
                    dto.setOrderId(orderItem.getOrder().getId());
                    dto.setProductId(orderItem.getProduct().getId());
                    return dto;
                })
                .orElse(null);
    }

    @Override
    public OrderItemDetailDTO createOrderItem(OrderItemDetailDTO orderItemDTO) {
        OrderItem orderItem = modelMapper.map(orderItemDTO, OrderItem.class);
        Order order = orderRepository.findById(orderItemDTO.getOrderId()).orElse(null);
        Product product = productRepository.findById(orderItemDTO.getProductId()).orElse(null);
        if (order != null) {
            orderItem.setOrder(order);
        }
        if (product != null) {
            orderItem.setProduct(product);
        }
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return modelMapper.map(savedOrderItem, OrderItemDetailDTO.class);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public List<OrderItemSummaryDTO> getOrderItemsByQuantityGreaterThan(int quantity) {
        return orderItemRepository.findByQuantityGreaterThan(quantity).stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemSummaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemSummaryDTO> getOrderItemsByPriceRange(double minPrice, double maxPrice) {
        return orderItemRepository.findByPriceBetween(minPrice, maxPrice).stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemSummaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemSummaryDTO> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId).stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemSummaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemSummaryDTO> getOrderItemsByProductId(Long productId) {
        return orderItemRepository.findByProductId(productId).stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemSummaryDTO.class))
                .collect(Collectors.toList());
    }
}
