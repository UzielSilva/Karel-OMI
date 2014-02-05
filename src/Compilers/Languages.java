/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Compilers;

/**
 *
 * @author Uziel
 */
public abstract class Languages {
    public static class Language{
        private int lang;
        protected KeyWord[] KeysArray;
        public Language(int lang){
            switch (lang){
                case 0:
                    KeysArray = Compilers.Pascal.Language.KeysES.KeysArray;
                    break;
                case 1:
                    KeysArray = Compilers.Pascal.Language.KeysEN.KeysArray;
                    break;
                case 2:
                    KeysArray = Compilers.Java.Language.KeysES.KeysArray;
                    break;
                case 3:
                    KeysArray = Compilers.Java.Language.KeysEN.KeysArray;
                    break;
            }
            this.lang = lang;
        }
        public boolean equals(Language l){
            return this.lang == l.lang;
        }
    }
    public static final Language PASCALES = new Language(0);
    
    public static final Language PASCALEN = new Language(1);
    
    public static final Language JAVAES = new Language(2);
    
    public static final Language JAVAEN = new Language(3);


}
