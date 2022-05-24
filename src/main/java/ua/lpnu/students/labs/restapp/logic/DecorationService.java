package ua.lpnu.students.labs.restapp.logic;

import java.util.Arrays;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lpnu.students.labs.restapp.dataaccess.database.DecorationRepository;
import ua.lpnu.students.labs.restapp.model.ElectricDecoration;
import ua.lpnu.students.labs.restapp.model.shared.Decoration;
import ua.lpnu.students.labs.restapp.model.shared.Type;
import ua.lpnu.students.labs.restapp.model.shared.Usage;

@Service
public class DecorationService {
    @Autowired
    DecorationRepository decorationRepository;
    
    public void writeA(){
        var dec = new Decoration(); 
        BeanUtils.copyProperties( dec, new ElectricDecoration("dddd", "dddd", Type.ELECTRIC_DECORATION, Usage.FOR_CHRISTMASS, 0, 0.0f, 0l, Arrays.asList("askjksa"), 0, 22, 12));
        decorationRepository.save(dec);

    }
}
