package com.example.tangshisongci.model.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigwen on 2015/12/26.
 */
public class ModelParse {
    private static String TAG = ModelParse.class.getName();
    public static String getCreatTableSql(Object o) {
        List<Field> fields = getDBFiledFromObject(o);
        Field primary = getDBPrimaryKeyField(fields);
        StringBuilder stringBuilder = new StringBuilder();
        String createString = "";
        if (primary != null) {
            fields.remove(primary);
            String primaryName = primary.getName();
            for (Field field : fields) {
                String type = getFieldType(field);
                stringBuilder.append(" " + field.getName() + " " + type + " ,");
            }
            String sql = stringBuilder.toString();
            sql = sql.length() > 0 ? sql.substring(0, sql.length() - 1) : "";
            createString = "create table if not exists " + getTableName(o) + " ( " +
                    " " + primaryName + " " + getFieldType(primary) + " primary key ," +
                    " " + sql + " ) ";
        } else {
            for (Field field : fields) {
                String type = getFieldType(field);
                stringBuilder.append(" " + field.getName() + " " + type + " , ");
                String sql = stringBuilder.toString();
                sql = sql.length() > 0 ? sql.substring(0, sql.length() - 1) : "";
                createString = "create table if not exists  " + getTableName(o) + "  ( " +
                        " " + sql + " ) ";
            }
        }
        return createString;
    }

    public static String getTableName(Object object){
        Annotation[] annotations =object.getClass().getAnnotations();
        String name = "";
        for(Annotation annotation:annotations){
            if(annotation instanceof MyAnnotion.DBName){
                name = ((MyAnnotion.DBName) annotation).value();
                break;
            }
        }
        Log.i(TAG, "getTableName "+name);
        if(name.isEmpty()){
            return object.getClass().getSimpleName();
        }else {
            return name;
        }
    }

    public static ContentValues toContentValues(Object o) {
        List<Field> fieldList = getDBFiledFromObject(o);
        ContentValues contentValues = new ContentValues();
        for (Field field : fieldList) {
            contentValues.put(field.getName(), getFieldValue(field, o));
        }
        return contentValues;
    }

    public static List<BaseModel> fromCursor(Cursor cursor, Object o) {
        List<Field> fieldList = getDBFiledFromObject(o);
        List<BaseModel> baseModelList = new ArrayList<BaseModel>();
        try {
            if (cursor.moveToFirst()) {
                do {
                    Class thisClass = o.getClass();
                    Object model = thisClass.newInstance();
                    BaseModel baseModel =(BaseModel)setValueFromCursor(cursor, fieldList, model);
                    baseModelList.add(baseModel);
                } while (cursor.moveToNext());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return baseModelList;
    }

    private static Object setValueFromCursor(Cursor cursor, List<Field> fields, Object o) {
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Class cl = field.getType();
            try {
                if (cl == int.class || cl == Integer.class) {
                    int value = cursor.getInt(cursor.getColumnIndex(name));
                    field.set(o, value);
                } else if (cl == long.class || cl == Long.class) {
                    long value = cursor.getLong(cursor.getColumnIndex(name));
                    field.set(o, value);
                } else if (cl == String.class) {
                    String value = cursor.getString(cursor.getColumnIndex(name));
                    field.set(o, value);
                }else {
                    throw new RuntimeException("filed is not int long String");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return o;
    }

    private static String getFieldValue(Field field, Object o) {
        boolean fieldAcc = field.isAccessible();
        if (!fieldAcc) {
            field.setAccessible(true);
        }
        try {
            Class c = field.getType();
            if (c == int.class || c == Integer.class) {
                return field.getInt(o) + "";
            }
            if (c == Long.class || c == long.class) {
                return field.getLong(o) + "";
            } else if (c == String.class) {
                return (String) field.get(o);
            } else {
                throw new RuntimeException("db field is not int long String");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            field.setAccessible(fieldAcc);
        }
    }


    //获取属性的数据类型
    private static String getFieldType(Field field) {
        String type = "";
        Class fieldType = field.getType();
        if (fieldType == int.class || fieldType == Integer.class || fieldType == long.class || fieldType == Long.class) {
            type = "integer";
        } else if (fieldType == String.class) {
            type = "text";
        } else {
            throw new RuntimeException("filedType is not int long String");
        }
        return type;
    }

    //解析出含有DBFiled注解的属性
    private static List<Field> getDBFiledFromObject(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        List<Field> fieldList = new ArrayList<Field>();
        for (Field field : fields) {
            //获取注解
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                //若有注解DBField添加
                if (annotation instanceof MyAnnotion.DBField) {
                    fieldList.add(field);
                    break;
                }
            }
        }
        if (fieldList.size() == 0) {
            throw new RuntimeException("no db field");
        }
        return fieldList;
    }

    //解析出含有DBPrimaryKey注解的属性
    private static Field getDBPrimaryKeyField(List<Field> fields) {
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof MyAnnotion.DBPrimaryKey) {
                    return field;
                }
            }
        }
        return null;
    }
}
