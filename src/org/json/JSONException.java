package org.json;// Note: this class was written without inspecting the non-free org.json sourcecode.
/**
 * Thrown to indicate a problem with the org.json.JSON API. Such problems include:
 * <ul>
 *   <li>Attempts to parse or construct malformed documents
 *   <li>Use of null as a name
 *   <li>Use of numeric types not available to org.json.JSON, such as {@link
 *       Double#isNaN() NaNs} or {@link Double#isInfinite() infinities}.
 *   <li>Lookups using an out of range index or nonexistent name
 *   <li>Type mismatches on lookups
 * </ul>
 *
 * <p>Although this is a checked exception, it is rarely recoverable. Most
 * callers should simply wrap this exception in an unchecked exception and
 * rethrow:
 * <pre>  public org.json.JSONArray toJSONObject() {
 *     try {
 *         org.json.JSONObject result = new org.json.JSONObject();
 *         ...
 *     } catch (org.json.JSONException e) {
 *         throw new RuntimeException(e);
 *     }
 * }</pre>
 */
public class JSONException extends Exception {
    public JSONException(String s) {
        super(s);
    }
}