package Simple_AddressBook;

public class Persons implements Comparable

        {
        String name;
        String email;
        String address;
        String phoneNumber;
        Persons()
        {
        name =  email = address = phoneNumber = "";
        }

        Persons(String Name, String email, String address, String phoneNumber)
        {

        this.name = Name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

        }

        String getName()
        {
        return name;
        }

        String getEmail()
        {
        return email;
        }

        String getAddress()
        {
        return address;
        }

        String getPhoneNumber()
        {
        return phoneNumber;
        }

        void setName(String name)
        {
        this.name = name;
        }

        void setEmail(String email)
        {
        this.email = email;
        }

        void setAddress(String address)
        {
        this.address = address;
        }

        void setPhoneNumber(String phoneNumber)
        {
        this.phoneNumber = phoneNumber;

        }

public String toString()
        {

        return " Name: " + name + " "  + " Email: " + email + " Address: " + address + " Phone Number: " + phoneNumber + "\n";
        }

@Override
public int compareTo(Object o)
        {

        return this.getName().compareTo(((Persons)o).getName());
        }

        }

