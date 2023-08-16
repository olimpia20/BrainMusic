package repository;

import music.model.Content;

import java.util.List;

public interface ContentRepository extends Repository<Content, Integer>{
    Content findByCoursPart(int nb_cours, int part);
    List<Content> findByCours(int nb_cours);
}
