package main.events;

import main.dto.SearchDTO;
import main.facts.Customer;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class NewSearchEvent {

    public long timestamp;
    public SearchDTO searchDTO;
    public Customer customer;

    public NewSearchEvent() {
    }

    public NewSearchEvent(SearchDTO searchDTO, Customer customer) {
//        this.timestamp = timestamp;
        this.searchDTO = searchDTO;
        this.customer = customer;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public SearchDTO getSearchDTO() {
        return searchDTO;
    }

    public void setSearchDTO(SearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
