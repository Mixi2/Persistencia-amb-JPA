package model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Records", schema = "DB", catalog = "")
public class RecordsEntity {
    private Integer ordinaryNote;
    private Integer extraordinaryNote;

    @Basic
    @Column(name = "ordinary_note", nullable = true)
    public Integer getOrdinaryNote() {
        return ordinaryNote;
    }

    public void setOrdinaryNote(Integer ordinaryNote) {
        this.ordinaryNote = ordinaryNote;
    }

    @Basic
    @Column(name = "extraordinary_note", nullable = true)
    public Integer getExtraordinaryNote() {
        return extraordinaryNote;
    }

    public void setExtraordinaryNote(Integer extraordinaryNote) {
        this.extraordinaryNote = extraordinaryNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordsEntity that = (RecordsEntity) o;
        return Objects.equals(ordinaryNote, that.ordinaryNote) && Objects.equals(extraordinaryNote, that.extraordinaryNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordinaryNote, extraordinaryNote);
    }
}
