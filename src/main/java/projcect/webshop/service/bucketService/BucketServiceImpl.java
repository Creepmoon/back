package projcect.webshop.service.bucketService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projcect.webshop.Dto.Requests.ProductRequest;
import projcect.webshop.Dto.model.BucketModel;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.Dto.model.ProductModel;
import projcect.webshop.Dto.model.SoldProductModel;
import projcect.webshop.dao.BucketDao;
import projcect.webshop.dao.PersonDao;
import projcect.webshop.dao.ProductDao;
import projcect.webshop.dao.SoldProductsDao;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService{

    private final BucketDao bucketDao;

    private final PersonDao personDao;

    private final ProductDao productDao;

    private final SoldProductsDao soldProductsDao;


    @Override
    public void CreateBucket(ProductRequest productRequest, PersonModel owner) {

        ProductModel productModel = productDao.GetProductModel(productRequest.getName());
        if(!bucketDao.GetUserBucket(owner).isStatus()){

            BucketModel bucketModel = BucketModel.builder()
                    .owner(owner)
                    .status(true)
                    .build();
            SoldProductModel soldProductModel = SoldProductModel.builder()
                    .productModel(productModel)
                    .bucketModel(bucketModel)
                    .build();
            bucketModel.setPrice(bucketModel.getPrice() + soldProductModel.getProductModel().getPrice());
            bucketDao.AddBucket(bucketModel);
        }
    }

    @Override
    public void UpdateBucket(ProductRequest productRequest, PersonModel owner){
        ProductModel productModel = productDao.GetProductModel(productRequest.getName());
        SoldProductModel soldProductModel = SoldProductModel.builder()
                .productModel(productModel)
                .bucketModel(bucketDao.GetUserBucket(owner))
                .build();
    }

    @Override
    public List<ProductModel> getBucket(BucketModel bucketModel, PersonModel owner) {
        List<SoldProductModel> soldProducts = soldProductsDao.getAllProductByOwner(bucketModel.getOwner());

        List<ProductModel> productModels = new ArrayList<>();
        for(SoldProductModel item: soldProducts){
            productModels.add(item.getProductModel());
        }
        return productModels;
    }



}
