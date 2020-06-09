package preprocessing;

import reduceExample.Atom;

import java.util.ArrayList;
import java.util.List;

public class Tool {
    public List<KeyWord> getKeyWord(Object object){
        System.out.println("object: "+object.getClass().getSimpleName());
        String type = object.getClass().getSimpleName();
        List<KeyWord> keyWordList = new ArrayList<>();
        switch(type){
            case "AssignExpr":{
                ((AssignExpression)object).getKeyWordList(keyWordList);

                return keyWordList;
            }
            case "BinaryExpr":{
//                BinaryExpression be = new BinaryExpression(element);
//
//                System.out.println(be);
                return keyWordList;
            }
            case "BooleanLiteralExpression":{
                BooleanLiteralExpression boe = (BooleanLiteralExpression) object;
                boe.getKeyWordList(keyWordList);
//                System.out.println(boe);
                return keyWordList;
            }
            case "Char":{
//                CharLiteralExpression cle = new CharLiteralExpression(element);
//                System.out.println(cle);
                return keyWordList;
            }
            case "Double":{
//                DoubleLiteralExpression dle = new DoubleLiteralExpression(element);
//
//                System.out.println(dle);
                return keyWordList;
            }
            case "FieldAccessExpr":{
//                FieldAccessExpression fae = new FieldAccessExpression(element);
//
//                System.out.println(fae);
                return keyWordList;
            }
            case "IntegerLiteralExpression":{
                IntegerLiteralExpression ile = (IntegerLiteralExpression) object;
                ile.getKeyWordList(keyWordList);
//                System.out.println(ile);
                return keyWordList;
            }
            case "Long":{
//                LongLiteralExpression lle = new LongLiteralExpression(element);
//
//                System.out.println(lle);
                return keyWordList;
            }
            case "MethodCallExpression":{
                MethodCallExpression mce = (MethodCallExpression) object;
                mce.getKeyWordList(keyWordList);
//                System.out.println(mce);
                return keyWordList;
            }
            case "Name":{
//                NameExpression ne = new NameExpression(element);
//
//                System.out.println(ne);
                return keyWordList;
            }
            case "SimpleName":{
//                SimpleNameExpression sne = new SimpleNameExpression(element);
//
//                System.out.println(sne);

                return keyWordList;
            }
            case "VariableDeclarationExpr":{
//                VariableDeclaratorExpression vde = new VariableDeclaratorExpression(element);
//
//                System.out.println(vde);
                return keyWordList;
            }
            case "StringLiteralExpr":{
//                StringLiteralExpression sle = new StringLiteralExpression(element);
//
//                System.out.println(sle);
                return keyWordList;
            }
            case "CastExpression":{
                CastExpression ce = (CastExpression) object;
                ce.getKeyWordList(keyWordList);
//                System.out.println(ce);
                return keyWordList;
            }
            case "VariableDeclaratorInfo":{
//                VariableDeclaratorInfo variableDeclaratorInfo = new VariableDeclaratorInfo(element);
//                System.out.println(variableDeclaratorInfo);
                VariableDeclaratorInfo vdi = (VariableDeclaratorInfo) object;
                vdi.getKeyWordList(keyWordList);
                return keyWordList;
            }
            default:{
                System.out.println("can't find this type!!!!!!!!!!!!");
                return null;
            }
        }
    }

    public List<Atom> getAtomList(Object object){
        List<Atom> atomList = new ArrayList<>();
        String type = object.getClass().getSimpleName();
        switch (type){
            case "MethodCallExpression" :{
                 MethodCallExpression mce= (MethodCallExpression) object;

            }
        }
        return atomList;
    }

}
