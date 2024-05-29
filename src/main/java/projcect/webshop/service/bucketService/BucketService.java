package projcect.webshop.service.bucketService;




import projcect.webshop.Dto.Requests.ProductRequest;
import projcect.webshop.Dto.model.BucketModel;
import projcect.webshop.Dto.model.PersonModel;
import projcect.webshop.Dto.model.ProductModel;

import java.util.List;

public interface BucketService {

    public void CreateBucket(ProductRequest productRequest, PersonModel owner);

    public void UpdateBucket(ProductRequest productRequest, PersonModel owner);

    public List<ProductModel> getBucket(BucketModel bucketModel, PersonModel owner);




}
