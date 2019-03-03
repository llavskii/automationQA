package tests;

public class Person {
    private static String name = "Vladimir Sorokin";
    private static String address = "17 Green st.";
    private static String city = "New Town";
    private static String state = "Omsk state";
    private static String zipCode = "78956";
    private static String cardType = "American Express";
    private static String creditCardNumber = "1111111111111111";
    private static String month = "5";
    private static String year = "2018";
    private static String nameOnCard = "Sorokin V";
    private static String flightNumber = null;

    public static String getName() {
        return name;
    }
    public static String getAddress() {
        return address;
    }

    public static String getCity() {
        return city;
    }

    public static String getState() {
        return state;
    }

    public static String getZipCode() {
        return zipCode;
    }

    public static String getCardType() {
        return cardType;
    }

    public static String getCreditCardNumber() {
        return creditCardNumber;
    }

    public static String getCreditCardMonth() {
        return month;
    }

    public static String getCreditCardYear() {
        return year;
    }

    public static String getNameOnCard() {
        return nameOnCard;
    }

    public static void setFlightNumber(String flightNumber) {
        Person.flightNumber = flightNumber;
    }
}
