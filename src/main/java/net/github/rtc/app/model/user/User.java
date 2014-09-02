package net.github.rtc.app.model.user;

import net.github.rtc.util.annotation.ForExport;
import net.github.rtc.util.annotation.Email;
import net.github.rtc.util.annotation.Maxlength;
import net.github.rtc.util.annotation.Required;
import net.github.rtc.util.annotation.Validatable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Validatable
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ForExport("Id")
    private long id;

    @Column
    @ForExport("Code")
    private String code;

    @Required
    @Maxlength(50)
    @Column
    @ForExport("Surname")
    private String surname;

    @Required
    @Maxlength(50)
    @Column
    @ForExport("Name")
    private String name;

    @Maxlength(50)
    @Column
    @ForExport("Middle name")
    private String middleName;

    @Required
    @Column
    @ForExport("Birthday")
    private Date birthDate;

    @Required
    @net.github.rtc.util.annotation.Number
    @Column
    @ForExport("Phone")
    private String phone;

    @Required
    @Email
    @Column
    @ForExport("Email")
    private String email;

    @Maxlength(30)
    @Column
    @ForExport("City")
    private String city;

    @Maxlength(30)
    @Column
    @ForExport("University")
    private String university;

    @Maxlength(30)
    @Column
    @ForExport("Faculty")
    private String faculty;

    @Maxlength(30)
    @Column
    @ForExport("Speciality")
    private String speciality;

    @Required
    @Column
    @ForExport("English")
    private String english;

    @Required
    @Column
    @ForExport("Note")
    private String note;

    @Required
    @Column
    private String password;

    @Column
    @ForExport("Gender")
    private String gender;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "UserProgLanguages", joinColumns = @JoinColumn(name = "user_id"))
    @ForExport("Programming Languages")
    private Set<String> programmingLanguages;

    @Required
    @Column
    @ForExport("Register Date")
    private Date registerDate;

    @Column
    private Date removalDate;

    @Required
    @Column
    @ForExport("Status")
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    /* Spring Security fields*/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    @ForExport("Role")
    private List<Role> authorities;
    @Column
    private boolean accountNonExpired = true;
    @Column
    private boolean accountNonLocked = true;
    @Column
    private boolean credentialsNonExpired = true;
    @Column
    private boolean enabled = true;


    public void setEnglish(String english) {
        this.english = english;
    }

    public String getEnglish() {
        return this.english;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setProgrammingLanguages(Set<String> progrLanguage) {
        this.programmingLanguages = progrLanguage;
    }

    public Set<String> getProgrammingLanguages() {
        return this.programmingLanguages;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
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

    public String getCity() {
        return city;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getRemovalDate() {
        return removalDate;
    }

    public void setRemovalDate(Date removalDate) {
        this.removalDate = removalDate;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    // for authentification
    public boolean hasRole(String role) {
        boolean hasRole = false;
        for (GrantedAuthority authority : this.getAuthorities()) {
            if (authority.getAuthority().equals(role)) {
                hasRole = true;
                break;
            }
        }
        return hasRole;
    }

    public boolean isForRemoval(){
        return this.status == UserStatus.FOR_REMOVAL;
    }


    public User() {
    }

    public User(String code, String surname, String name, String middleName,
                String phone, String email, Date birthDate, String city,
                String university, String faculty, String speciality,
                String note, String password, String gender,
                Set<String> programmingLanguages, String english) {

        this.code = code;
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

        this.note = note;
        this.password = password;
        this.gender = gender;
        this.programmingLanguages = programmingLanguages;
        this.english = english;
    }

    public User(String surname, String name, String middleName, String phone,
                String email, Date birthDate, String city, String university,
                String faculty, String speciality, String note, String password,
                String gender, Set<String> programmingLanguages, String english) {
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
        this.note = note;
        this.password = password;
        this.gender = gender;
        this.programmingLanguages = programmingLanguages;
        this.english = english;
    }

    public User(String name, String surname, String middleName, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
    }

    public String shortString(){
        return  new StringBuilder(this.name).append(" ").append(this.surname).append(" ").append(this.email).toString();
    }
}
