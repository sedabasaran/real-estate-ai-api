package com.realestate.finder.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorities")
public class Favorite {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id") // Foreign key sütun adı
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "property_id") // Foreign key sütun adı
    private Property property;

    @Column(nullable = false, updatable = false) // 'updatable = false' ekledik
    private LocalDateTime createdAt;

    public Favorite() {
    }

    // Bu constructor'ı genellikle kullanmana gerek kalmaz çünkü createdAt otomatik ayarlanır
    // public Favorite(User user, Property property, LocalDateTime createdAt) {
    //     this.user = user;
    //     this.property = property;
    //     this.createdAt = createdAt;
    // }

    // Yeni bir favori oluştururken sadece user ve property alanlarını kullanacağımız constructor
    public Favorite(User user, Property property) {
        this.user = user;
        this.property = property;
        // createdAt alanı @PrePersist ile otomatik doldurulacak
    }


    // --- ÖNEMLİ DEĞİŞİKLİK BURADA BAŞLIYOR ---

    // Veritabanına kaydedilmeden hemen önce createdAt alanını otomatik olarak doldurur
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // --- ÖNEMLİ DEĞİŞİKLİK BURADA BİTİYOR ---


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Property getProperty() {
        return property;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    // createdAt alanı otomatik doldurulduğu için bu setter'a genellikle ihtiyaç duyulmaz.
    // Ancak yine de bırakılabilir, elle ayarlamak istersen diye.
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
