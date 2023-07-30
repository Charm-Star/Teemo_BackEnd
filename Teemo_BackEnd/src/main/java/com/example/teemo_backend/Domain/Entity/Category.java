package com.example.teemo_backend.Domain.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {

    //카테고리 종류

    Food("식비"),
    ETC("기타");
    private String conCategory;

    public static String of(String code){

        if(code == null){
            throw new IllegalArgumentException();
        }

        for(Category c: Category.values()){
            if(c.conCategory.equals(code)){
                return c.conCategory;
            }
        }

        throw  new IllegalArgumentException("카테고리 불일치");
        
    }


}
