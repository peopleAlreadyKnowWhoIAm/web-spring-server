package ua.lpnu.students.labs.restapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Table("image-decoration")
public class ImageId {

    @Column("imageId")
    Long imageId;
}
