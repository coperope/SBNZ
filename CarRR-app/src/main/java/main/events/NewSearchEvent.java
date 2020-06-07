package main.events;

import main.dto.SearchDTO;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class NewSearchEvent {

    public long timestamp;
    public SearchDTO searchDTO;

    public NewSearchEvent() {
    }

    public NewSearchEvent(SearchDTO searchDTO) {
//        this.timestamp = timestamp;
        this.searchDTO = searchDTO;
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
}
