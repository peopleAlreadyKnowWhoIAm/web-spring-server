package ua.lpnu.students.labs.restapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("image")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Image {
   @Id
   long id;
   byte[] image; 
   
   public Image(byte[] image){
    this.image = image;
   }
}
