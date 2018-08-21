package pl.parser.npb.Model;
import java.util.List;

public class Response {
 private List<Double> buyRateList;
 private List<Double> sellRateList;
 public Response() {
		// TODO Auto-generated constructor stub
   super();
 }
 public List<Double> getBuyRateList() {
	return buyRateList;
 }

 public void setBuyRateList(Double buyRate) {
	this.buyRateList.add(buyRate);
 }

 public List<Double> getSellRateList() {
	return sellRateList;
 }

 public void setSellRateList(Double sellRate) {
	this.sellRateList.add(sellRate);
 }

}