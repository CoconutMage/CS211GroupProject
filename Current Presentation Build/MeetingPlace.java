/*
 * The MeetingPlace class stores all generic information about a possible place to meet.
 * It is used explicitly when the controller class finds places around the calculated midpoint.
 * Any subclasses include additional information that may not exist for all meeting places.
*/

public class MeetingPlace extends Location {
 // Declares attributes.
 private String name;
 private String address;
 private String placeType;
 private String starRating;
 
 // Declares multiple overloaded constructors depending on what information can be retrieved.
 public MeetingPlace(String lat, String lng, String name, String address, String placeType, String starRating) {
  super(lat, lng);
  this.setName(name);
  this.address = address;
  this.placeType = placeType;
  this.starRating = starRating;
 }
 
 public MeetingPlace(String name, String address, String placeType, String starRating) {
  super();
  this.setName(name);
  this.address = address;
  this.placeType = placeType;
  this.starRating = starRating;
 }
 
 public MeetingPlace(String name, String address, String placeType) {
  super();
  this.setName(name);
  this.address = address;
  this.placeType = placeType;
  this.starRating = "";
 }
 
 public MeetingPlace(String name, String address) {
  super();
  this.setName(name);
  this.address = address;
  this.placeType = "";
  this.starRating = "";
 }

 /* 
  * Note: As all attributes are Strings, a Meeting Place constructor would not recognize
  * arguments for an name, address, and starRating alone. In the case where a starRating
  * exists but a placeType doesn't, one should use the constructor that accepts a name
  * and address only and then apply the setStarRating method.
  */
 
 // Declares setters and getters for all attributes.
 public String getName() {
  return name;
 }
 public void setName(String name) {
  this.name = name;
 }
 
 public String getAddress() {
  return address;
 }
 public void setAddress(String address) {
  this.address = address;
 }
 
 public String getStarRating() {
  return starRating;
 }
 public void setStarRating(String starRating) {
  this.starRating = starRating;
 }
 
 public String getPlaceType() {
  return placeType;
 }
 public void setPlaceType(String placeType) {
  this.placeType = placeType;
 }
 
 // Similar to toString(). Lists all attributes on a new line.
 public String displayDetails() {
  String details = "";
  if (starRating != "") {
   details += "Rated: " + starRating + " out of 5" + "/";
  }
  return details;
 }

 
 
}