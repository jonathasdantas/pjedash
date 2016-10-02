package br.jus.pjedash.dto;

import br.jus.pjedash.entidade.Tag;

public class TagDTO {

    private Tag tag;
    
    private Long hits;

    public TagDTO(Tag tag) {
        this.tag = tag;
    }
    
    public TagDTO(Tag tag, Long hits) {
        this(tag);
        this.hits = hits;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Long getHits() {
        return hits;
    }

    public void setHits(Long hits) {
        this.hits = hits;
    }
    
}
