package kt.pet.domain.profile.entity;


import jakarta.persistence.*;
import kt.pet.common.EntityDate;
import kt.pet.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pet_profiles") // ✅ 테이블명 수정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PetProfile extends EntityDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "type")
    private String type;

    @Column(name = "imageUrl")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    public PetProfile(String name, String age, String type, String imageUrl, User user) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.imageUrl = imageUrl;
        this.user = user;
    }


}