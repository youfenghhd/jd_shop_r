package com.example.demo.service.serviceImpl;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Commodity;
import com.example.demo.mapper.CartMapper;
import com.example.demo.service.CartService;
import com.example.demo.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public List<Cart> getAll() {
        return cartMapper.getAll();
    }

    @Override
    public int addInfo(Cart cart) {
        return cartMapper.addInfo(cart);
    }

    @Override
    public int deleteInfo(int caid) {
        return cartMapper.deleteInfo(caid);
    }
}
