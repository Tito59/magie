/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiemagie;

/**
 *
 * @author Formation
 */
public class Carte {

    private TypeCarte type;

    public enum TypeCarte {
        BAVE_DE_CRAPAUD,
        AILE_DE_CHAUVE_SOURIS,
        LAPIS_LAZULI,
        MANDRAGORE,
        CORNE_DE_LICORNE
    }

    public Carte() {
    }

    public Carte(TypeCarte type) {
        this.type = type;
    }

    @Override// un arbre
    public boolean equals(Object obj) {
        
        if( obj instanceof Carte.TypeCarte ){
            
            return this.type.equals( obj );
        } else {
            Carte carteParam = (Carte) obj;
        return this.getType() == carteParam.getType();
        }
    }

    public TypeCarte getType() {
        return type;
    }

    public void setType(TypeCarte type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

}
