package Service;

import Domain.Province;

import java.util.List;

public interface ProvincesService {
     public List<Province> findAll();
     public String findAllJson();
}
