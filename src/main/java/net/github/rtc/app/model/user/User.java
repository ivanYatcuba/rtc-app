package net.github.rtc.app.model.user;

import net.github.rtc.app.model.AbstractPersistenceObject;
import net.github.rtc.util.annotation.ForExport;
import net.github.rtc.util.annotation.validation.*;
import net.github.rtc.util.annotation.validation.Number;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Validatable
public class User extends AbstractPersistenceObject implements UserDetails {

    public static final int PRIMARY_LENGTH = 50;
    public static final int SECONDARY_LENGTH = 30;
    public static final String STRING_SPACE = " ";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ForExport("Id")
    private long id;

   /* @Column
    private String code;*/

    @Required
    @Maxlength(PRIMARY_LENGTH)
    @Column
    @ForExport("Surname")
    private String surname;

    @Required
    @Maxlength(PRIMARY_LENGTH)
    @Column
    @ForExport("Name")
    private String name;

    @Maxlength(PRIMARY_LENGTH)
    @Column
    @ForExport("Middle name")
    private String middleName;

    @Required
    @Column
    @ForExport("Birthday")
    private Date birthDate;

    @Required
    @Number
    @Column
    @ForExport("Phone")
    private String phone;

    @Required
    @Email
    @Column
    @ForExport("Email")
    private String email;

    @Maxlength(SECONDARY_LENGTH)
    @Column
    @ForExport("City")
    private String city;

    @Maxlength(SECONDARY_LENGTH)
    @Column
    @ForExport("University")
    private String university;

    @Maxlength(SECONDARY_LENGTH)
    @Column
    @ForExport("Faculty")
    private String faculty;

    @Maxlength(SECONDARY_LENGTH)
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
    @ForExport("Photo")
    private String photo;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "UserProgLanguages",
      joinColumns = @JoinColumn(name = "user_id"))
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
      joinColumns = { @JoinColumn(name = "USER_ID") },
      inverseJoinColumns = { @JoinColumn(name = "id") })
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

    public User() {
    }

    public User(
      final String name,
      final String surname,
      final String middleName,
      final String email,
      final String password) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
    }

    public void setEnglish(final String english) {
        this.english = english;
    }

    public String getEnglish() {
        return this.english;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setPhoto(final String photo) {
        this.photo = photo;
    }

    public String getPhoto() { return  this.photo; }


    @Override
    public String getUsername() {
        return email;
    }

    public void setProgrammingLanguages(final Set<String> progrLanguage) {
        this.programmingLanguages = progrLanguage;
    }

    public Set<String> getProgrammingLanguages() {
        return this.programmingLanguages;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(final List<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(final boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(final boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(final boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(final String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(final String faculty) {
        this.faculty = faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(final String speciality) {
        this.speciality = speciality;
    }

    public String getNote() {
        return note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

/*    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }*/

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(final Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getRemovalDate() {
        return removalDate;
    }

    public void setRemovalDate(final Date removalDate) {
        this.removalDate = removalDate;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(final UserStatus status) {
        this.status = status;
    }

    // for authentification
    public boolean hasRole(final String role) {
        boolean hasRole = false;
        for (final GrantedAuthority authority : this.getAuthorities()) {
            if (authority.getAuthority().equals(role)) {
                hasRole = true;
                break;
            }
        }
        return hasRole;
    }

    public boolean isForRemoval() {
        return this.status == UserStatus.FOR_REMOVAL;
    }

    public boolean isActive() {
        return this.status == UserStatus.ACTIVE;
    }


    public String shortString() {
        return new StringBuilder(this.name).append(STRING_SPACE).append(
          this.surname).append(STRING_SPACE).append(this.email).toString();
    }
}
