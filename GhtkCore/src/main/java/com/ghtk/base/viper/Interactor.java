package com.ghtk.base.viper;

import android.util.Log;

import com.ghtk.base.viper.interfaces.IInteractor;
import com.ghtk.base.viper.interfaces.IPresenter;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Base Interactor
 * Created by neo on 8/29/2016.
 */
public abstract class Interactor<P extends IPresenter> implements IInteractor<P> {
  protected P mPresenter;

  public Interactor(P presenter) {
    mPresenter = presenter;
  }

  public JSONObject getJSONObjectFromJSON(String key, JSONObject object){
    JSONObject result = null;
    try{
      if (object.has(key) && !object.isNull(key)){
        Object tmpObject = object.get(key);
        if (tmpObject instanceof JSONObject){
          result = (JSONObject) tmpObject;
        }
      }
    }
    catch (Exception e){
      error(e.getMessage());
      result = null;
    }
    finally {
      if (result == null){
        result = new JSONObject();
      }
      return result;
    }
  }

  public JSONArray getJSONArrayFromJSON(String key, JSONObject object){
    JSONArray result = null;
    try{
      if (object.has(key) && !object.isNull(key)){
        result = object.getJSONArray(key);
      }
    }
    catch (Exception e){
      error(e.getMessage());
      result = null;
    }
    finally {
      if (result == null){
        return new JSONArray();
      }
      return result;
    }
  }

  public JSONObject getJSONObjectInJSONArrayAtIndex(int index, JSONArray array){
    JSONObject result = null;
    try{
      result = array.getJSONObject(index);
    }
    catch (Exception e){
      error(e.getMessage());
      result = null;
    }
    finally {
      return result;
    }
  }

  public int getIntFromJSON(String key, JSONObject object){
    int result = 0;
    try{
      if (object.has(key) && !object.isNull(key)){
        result = object.getInt(key);
      }
    }
    catch (Exception e){
      error(e.getMessage());
      result = 0;
    }
    finally {
      return result;
    }
  }

  public Long getLongFromJSON(String key, JSONObject object){
    Long result = 0l;
    try{
      if (object.has(key) && !object.isNull(key)){
        result = object.getLong(key);
      }
    }
    catch (Exception e){
      error(e.getMessage());
      result = 0l;
    }
    finally {
      return result;
    }
  }

  public double getDoubleFromJSON(String key, JSONObject object){
    double result = 0;
    try{
      if (object.has(key) && !object.isNull(key)){
        result = object.getDouble(key);
      }
    }
    catch (Exception e){
      error(e.getMessage());
      result = 0;
    }
    finally {
      return result;
    }
  }

  protected String getStringFromJSON(String key, JSONObject object){
    String result = "";
    try{
      if (object.has(key) && !object.isNull(key)){
        result = object.getString(key);
      }
    }
    catch (Exception e){
      error(e.getMessage());
      result = "";
    }
    finally {
      return result;
    }
  }

  public boolean getBooleanFromJSON(String key, JSONObject object){
    boolean result = false;
    try{
      if (object.has(key) && !object.isNull(key)){
        result = object.getBoolean(key);
      }
    }
    catch (Exception e){
      error(e.getMessage());
      result = false;
    }
    finally {
      return result;
    }
  }

  public static void error(String msg) {
    if (msg == null) {
      msg = "[]";
    }
    if (msg.equals("")) {
      msg = "[]";
    }
    Log.e("[ERROR]" + getTag(), msg);
  }

  protected static String getTag() {
    try {
      StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];

      return "[" + stackTraceElement.getClassName().substring(stackTraceElement.getClassName().lastIndexOf(".") + 1) + "][" + stackTraceElement.getMethodName() + "][" + stackTraceElement.getLineNumber() + "]";
    } catch (Exception e) {
      return "[UNKNOW]";
    }

  }
}
