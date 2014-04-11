package net.github.rtc.app.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.util.rtc.annotation.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Date;
//import java.util.List;
//import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User implements UserDetails {

    Integer id;
    @required
    @maxlength(50)
    @minlength(5)
    private String surname;

    @required
    @maxlength(50)
    @minlength(5)
    private String name;  
    
    @required
    @maxlength(50)
    @minlength(5)
    private String middleName;
    @required
    @number
    private String phone;
    @email
    private String email;
    @date
    @required
    private Date birthDate;

    @maxlength(30)
    @minlength(5)
    @required
    private String city;

    @maxlength(30)
    @minlength(5)
    @required
    private String university;
    @maxlength(30)
    @minlength(5)
    @required
    private String faculty;
    @maxlength(30)
    @minlength(5)
    @required
    private String speciality;

    
    // private String technologies;
    @number
    private Integer writtenEng;
    @number
    private Integer oralEng;
    private String note;
    private String password;
    private String gender;
    private String progLanguages;
    private String english;

    /* Spring Security fields*/
    private Collection<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

  
    
    public void setEnglish(String english)
    {
        this.english=english;
    }
    public String getEnglish()
    {
        return this.english;
    }
    
    public void setGender(String gender)
    {
        this.gender=gender;
    }
    
    public String getGender()
    {
        return this.gender;
    }
    
    @Override
    public String getUsername() {
        return email;
    }

     
    public void setProgLanguages(String progrLanguage)
    {
        this.progLanguages=progrLanguage;
    }
    
    /**
     *
     * @return
     */
    public String getProgLanguages()
    {
       return this.progLanguages;
    }
    
    
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * *********************************
     * @return 
     */

    public String getCity() {
        return city;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    //maybe this getter maybe should be changed
    //I have no idea how to pass several technologies from a reg.form to a controller and user
   /* public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }
*/
    public Integer getWrittenEng() {
        return writtenEng;
    }

    public void setWrittenEng(Integer writtenEng) {
        this.writtenEng = writtenEng;
    }

    public Integer getOralEng() {
        return oralEng;
    }

    public void setOralEng(Integer oralEng) {
        this.oralEng = oralEng;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }




    public User() {

    }

    public User(Integer id, String surname, String name, String middleName, String phone, String email, Date birthDate, String city, String university, String faculty, String speciality, Integer writtenEng, Integer oralEng, String note, String password, String gender, String progLanguages, String english) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.city = city;
        this.university = university;
        this.faculty = faculty;
        this.speciality = speciality;
        this.writtenEng = writtenEng;
        this.oralEng = oralEng;
        this.note = note;
        this.password = password;
        this.gender = gender;
        this.progLanguages = progLanguages;
        this.english = english;
    }

    public User(String surname, String name, String middleName, String phone, String email, Date birthDate, String city, String university, String faculty, String speciality, Integer writtenEng, Integer oralEng, String note, String password, String gender, String progLanguages, String english) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.city = city;
        this.university = university;
        this.faculty = faculty;
        this.speciality = speciality;
        this.writtenEng = writtenEng;
        this.oralEng = oralEng;
        this.note = note;
        this.password = password;
        this.gender = gender;
        this.progLanguages = progLanguages;
        this.english = english;
    }
}