package cn.tedu.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.pojo.Product;

public interface ProductMapper {

	int queryTotal();

	List<Product> queryPage(@Param("start")int start, @Param("rows")Integer rows);

	Product queryById(String productId);

	void saveProduct(Product product);

	void updateProduct(Product product);

}
