package com.leventergoren.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotEmpty(message = "Kullanıcı Adı Boş Olamaz!")
    @Length(min = 3, max = 30, message = "Kullanıcı adı 3 ile 20 karakter arasında olmalıdır")
    private String username;

    @NotEmpty(message = "Şifre Boş Olamaz!")
    @Length(min = 4, message = "Şifre en az 4 karakterden oluşmalıdır.")
    private String password;

    @NotEmpty(message = "İsim Boş Olamaz!")
    @Length(min = 2, message = "İsim en az 2 karakterden oluşmalıdır.")
    private String firstname;

    @NotEmpty(message = "Soyisim Boş Olamaz!")
    @Length(min = 2, message = "Soyisim en az 2 karakterden oluşmalıdır.")
    private String lastname;

    @NotEmpty(message = "Email Boş Olamaz!")
    @Email(message = "Email formatına uygun olmalıdır.")
    private String email;

}
