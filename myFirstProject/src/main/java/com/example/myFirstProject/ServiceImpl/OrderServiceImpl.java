package com.example.myFirstProject.ServiceImpl;

import com.example.myFirstProject.dto.OrderDetailDTO;
import com.example.myFirstProject.dto.OrderSummaryDTO;
import com.example.myFirstProject.model.Order;
import com.example.myFirstProject.repository.OrderRepository;
import com.example.myFirstProject.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderSummaryDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrderSummaryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(order -> modelMapper.map(order, OrderDetailDTO.class))
                .orElse(null);
    }

    @Override
    public OrderDetailDTO createOrder(OrderDetailDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDetailDTO.class);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
