/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Compilers;

import Compilers.Pascal.Languaje.*;

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
                    KeysArray = KeysEN.KeysArray;
                    break;
                case 1:
                    KeysArray = KeysES.KeysArray;
                    break;
            }
            this.lang = lang;
        }
        public boolean equals(Language l){
            return this.lang == l.lang;
        }
    }
    public static final Language EN = new Language(0);
    
    public static final Language ES = new Language(1);

}
