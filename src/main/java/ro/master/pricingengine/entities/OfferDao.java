package ro.master.pricingengine.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OFFERS")
public class OfferDao {

    private Long id;

    private String name;

    private String description;

    private List<ChannelDao> channels;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "offer_channels",
            joinColumns = {@JoinColumn(name = "offer_id")},
            inverseJoinColumns = {@JoinColumn(name = "channel_id")}
    )
    public List<ChannelDao> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelDao> channels) {
        this.channels = channels;
    }
}
