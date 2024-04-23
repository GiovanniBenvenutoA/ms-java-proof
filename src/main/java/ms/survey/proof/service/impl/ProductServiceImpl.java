package ms.survey.proof.service.impl;

import ms.survey.proof.DTO.ProductDto;
import ms.survey.proof.DTO.ResponseProduct;
import ms.survey.proof.entities.Product;
import ms.survey.proof.repository.ProductRepository;
import ms.survey.proof.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseEntity<ResponseProduct> addProduct(ProductDto productDto) {
        ResponseProduct responseProduct = new ResponseProduct();
        try {

            if(productDto!=null){
                Product product = productRepository.findByProduct(productDto.getProductName());
                if(product== null){
                    Product products = modelMapper.map(productDto, Product.class);
                    productRepository.save(products);
                    responseProduct.setMessage("Producto creado");
                    responseProduct.setCode("0");
                    return new ResponseEntity<>(responseProduct,HttpStatus.CREATED);
                }else{
                    responseProduct.setMessage("Producto existe");
                    responseProduct.setCode("1");
                    return new ResponseEntity<>(responseProduct,HttpStatus.BAD_REQUEST);
                }

            }
            responseProduct.setMessage("Producto vacio");
            responseProduct.setCode("-2");
            return new ResponseEntity<>(responseProduct,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            responseProduct.setMessage("Error inesperado");
            responseProduct.setCode("-1");
            return new ResponseEntity<>(responseProduct,HttpStatus.NOT_FOUND);
        }


    }

    @Override
    public ResponseEntity<ResponseProduct> getProduct(Map<String, String> requestBody) {
        ResponseProduct responseProduct = new ResponseProduct();
        try {
            String productName = requestBody.get("productName");

            if (productName == null || productName.isEmpty()) {
                responseProduct.setCode("-1");
                responseProduct.setMessage("El parámetro 'nombreProducto' es requerido");
                return new ResponseEntity<>(responseProduct,HttpStatus.BAD_REQUEST);
            }else{
                List<Product> resultProduct =productRepository.findByProductNameLike(productName);
                if(!resultProduct.isEmpty()){
                    List<ProductDto> productDTOs = resultProduct.stream()
                            .map(product -> modelMapper.map(product, ProductDto.class))
                            .collect(Collectors.toList());
                    responseProduct.setCode("0");
                    responseProduct.setMessage("Se encontraron registros de productos");
                    responseProduct.setProduct(productDTOs);
                    return new ResponseEntity<>(responseProduct,HttpStatus.BAD_REQUEST);
                }else{
                    responseProduct.setCode("1");
                    responseProduct.setMessage("No se encontraron registros de productos");
                    return new ResponseEntity<>(responseProduct,HttpStatus.BAD_REQUEST);
                }



            }
        }catch (Exception e)
        {
            responseProduct.setMessage("Error inesperado");
            responseProduct.setCode("-2");
            return new ResponseEntity<>(responseProduct,HttpStatus.NOT_FOUND);
        }
    }
}
