package net.github.rtc.app.service;

/**
 * Created by denis on 13.10.14.
 */
public interface RtcPasswordEncoder {
    /**
     * this method should use a good encoding algorithm like SHA-1 or greater
     * @param rawPassword encode and return given password (char[] or String)
     */
    String encode(CharSequence rawPassword);

    /**
     * this method compares regular entered string with certain already encoded password
     * @param rawPassword password to be encoded and compared with
     * @param encodedPassword already encoded string
     * @return true, if encoded rawPassword matches to given encodedPassword
     */
    boolean matches(CharSequence rawPassword, String encodedPassword);
}

