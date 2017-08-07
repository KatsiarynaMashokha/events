import com.sun.scenario.effect.Blend;
import models.Event;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {

        get("/", (req, res) -> {
            Map<String, Object> model= new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("finalEvent", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Integer numGuests = Integer.parseInt(request.queryParams("guestNum"));
            Integer food = Integer.parseInt(request.queryParams("food"));
            Integer drinks = Integer.parseInt(request.queryParams("drink"));
            Integer entertainment = Integer.parseInt(request.queryParams("music"));
            Integer promo = Integer.parseInt(request.queryParams("coupon"));

            Event event = new Event();
            Integer guestCost = event.costOfGuest(numGuests);
            Integer foodCost = event.calculateFoodCost(food);
            Integer drinkCost = event.calculateDrinksCost(drinks);
            Integer entertainmentCost = event.calculateEntertainmentCost(entertainment);

            Integer finalPrice = guestCost + foodCost + drinkCost + entertainmentCost;
            Integer newTotal = event.calculateCoupon(finalPrice, promo);

            model.put("totalCost", newTotal);
            return new ModelAndView(model, "finalEvent.hbs");
        }, new HandlebarsTemplateEngine());




//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Welcome to Haneen's Event Planning Service!" + '\n' + "You will be prompted with a few event options to get you an accurate event quote!" + '\n');
//        try{
//            String again;
//            String couponAgain;
//
//            do{
//                //Guest Price
//                System.out.println("Enter the number of your expected attendees: ");
//                int guestNumInput = Integer.parseInt(bufferedReader.readLine());
//                Event event = new Event();
//                int finalCost = event.costOfGuest(guestNumInput);
//                System.out.println("Your cost for " + guestNumInput+ " guests would amount to $" + finalCost +"." + '\n' + "Select the Enter key to continue.");
//                System.in.read(); //wait for the user to select any key before displaying more options
//
//                //Food Price
//
//                System.out.println("Choose one of the following food options, by entering the corresponding number: " + '\n' + "1. Italian Cuisine" + '\n' + "2. Asian Fusion" + '\n' + "3. Mediterranean Grill" + '\n' + "4. All American" + '\n' + "5. Indian Experience");
//
//                Integer foodChoiceInput = Integer.parseInt(bufferedReader.readLine());
//
//                if (foodChoiceInput.equals(1) || foodChoiceInput.equals(2) || foodChoiceInput.equals(3) || foodChoiceInput.equals(4) || foodChoiceInput.equals(5)) {
//                    finalCost += event.calculateFoodCost(foodChoiceInput);
//                    System.out.println('\n' + "Your total cost for food option " + foodChoiceInput + " and " + guestNumInput + " guest(s) is $" + finalCost + "." + '\n' + "Select the Enter key to continue.");
//                    System.in.read();
//                } else {
//                    System.out.println("You entered an invalid input");
//                    break;
//                }
//
//
//                //Drinks Price
//                System.out.println("Choose one of the following drink options, by entering the corresponding number: " + '\n' + "1. Italian Sodas" + '\n' + "2. Thai Tea" + '\n' + "3. Mint Lemonade" + '\n' + "4. Soda Assortments" + '\n' + "5. Chai and Lemon Water");
//                Integer drinkChoice = Integer.parseInt(bufferedReader.readLine());
//
//
//                finalCost += event.calculateDrinksCost(drinkChoice);
//                System.out.println('\n' + "Your total cost for drink option " + drinkChoice + ", food option " + foodChoiceInput + " and " + guestNumInput + " guest(s) is $" + finalCost + "." + '\n' + "Select the Enter key to continue.");
//                System.in.read();
//
//
//                //Entertainment Price
//                System.out.println("Choose one of the following entertainment options, by entering the corresponding number: " + '\n' + "1. DJ" + '\n' + "2. Live Band" + '\n' + "3. No Entertainment/You will provide your own");
//                int entertainmentChoice = Integer.parseInt(bufferedReader.readLine());
//                finalCost += event.calculateEntertainmentCost(entertainmentChoice);
//                System.out.println('\n' + "Your total cost for entertainment option " + entertainmentChoice + ", drink option "+ drinkChoice + ", food option " +foodChoiceInput+ " and " +guestNumInput + " guest(s) is $" +finalCost + "." + '\n' + "Select the Enter key to continue.");
//                System.in.read();
//
//                //Apply Coupons
//
//                    System.out.println("Would you like to checkout our current coupons (Yes | No)?");
//                    String coupon = bufferedReader.readLine();
//                    if (coupon.equals("Yes")){
//                        do {
//                            System.out.println("Here are our current options:" + '\n' + "1. $100 off your entire package of $1000 or more!" +'\n' + "2. 20% off your package of 2,000 or more" +'\n' + "Please enter the corresponding number: ");
//                            Integer couponChoice = Integer.parseInt(bufferedReader.readLine());
//                            if (couponChoice.equals(1) && finalCost >= 1000){
//                                finalCost = event.calculateCoupon(finalCost, couponChoice);
//                                System.out.println("That worked! Your new total is $" + finalCost);
//                                couponAgain = "no";
//                            }else if (couponChoice.equals(1) && finalCost < 1000){
//                                System.out.println("Sorry this coupon cannot be applied since your total is less than $1000.");
//                                couponAgain = tryAgain();
//                            }else if (couponChoice.equals(2) && finalCost >= 2000){
//                                finalCost = event.calculateCoupon(finalCost, couponChoice);
//                                System.out.println("That worked! Your new total is $" + finalCost);
//                                couponAgain = "no";
//                            }else if (couponChoice.equals(2) && finalCost < 2000){
//                                System.out.println("Sorry this coupon cannot be applied since your total is less than $2000.");
//                                couponAgain = tryAgain();
//                            } else{
//                                System.out.println("Invalid Input");
//                                couponAgain = tryAgain();
//                            }
//                        } while (couponAgain.equals("Yes"));
//
//                    } else if (coupon.equals("No")){
//
//                    } else{
//                        System.out.println("Sorry you did not enter a valid choice.");
//
//                    }
//
//
//                System.out.println("Would you like to try get another event quote (Yes | No)? ");
//                again = bufferedReader.readLine();
//            } while (again.equals("Yes"));
//
//        }catch (IOException e){
//           e.printStackTrace();
//        }
//    }
//    public static String tryAgain() throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Would you like to try again (Yes | No)? ");
//        String couponAgain = bufferedReader.readLine();
//        return couponAgain;
    }
}
