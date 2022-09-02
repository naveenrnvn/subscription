package com.subscription.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.subscription.Exceptions.AddSubscriptionFailed;
import com.subscription.entity.AddSubscription;
import com.subscription.entity.Plans;
import com.subscription.entity.ReneValRemainder;
import com.subscription.entity.Subscriptions;
import com.subscription.entity.TopUp;

@Service
public class SubscriptionService {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

	HashMap<String, Plans> music = new HashMap<>();
	HashMap<String, Plans> video = new HashMap<>();
	HashMap<String, Plans> podcast = new HashMap<>();

	public ArrayList<ReneValRemainder> addSubscription(Subscriptions subs) {

		validateJson(subs);
		int renval = 0;

		TopUp up = subs.getTopUp().size() > 0 ? subs.getTopUp().get(0) : null;
		ArrayList<ReneValRemainder> response = new ArrayList<>();
		subs.getPlans().stream().filter((plan) -> plan.getSubscriptionCatogary().equals("music")).forEach((plan) ->
        
		response.add(new ReneValRemainder(addPodCastSub(plan, up, subs.getDate())))

		);
		subs.getPlans().stream().filter((plan) -> plan.getSubscriptionCatogary().equals("video"))
				.forEach((plan) -> response.add(new ReneValRemainder(addPodCastSub(plan, up, subs.getDate()))));
		subs.getPlans().stream().filter((plan) -> plan.getSubscriptionCatogary().equals("podcast"))
				.forEach((plan) -> response.add(new ReneValRemainder(addPodCastSub(plan, up, subs.getDate()))));

		return response;
	}

	public void isValidPlan(String planName) {
		if (!music.containsKey(planName)) {
			throw new AddSubscriptionFailed("ADD_SUBSCRIPTION_FAILED", " : PLAN_NOT_EXISTS");
		}
	}

	public String addMusicSub(AddSubscription plan, TopUp topUp, String date) {

		isValidPlan(plan.getPlanName());
		try {
			return "RENEWAL_REMINDER MUSIC " + addMonths(date, music.get(plan.getPlanName()).getValidity());
		} catch (ParseException e) {

			throw new AddSubscriptionFailed("ADD_SUBSCRIPTION_FAILED", " : INVALID_DATE");

		}

	}

	public String addVideoSub(AddSubscription plan, TopUp topUp, String date) {
		isValidPlan(plan.getPlanName());

		try {
			return "RENEWAL_REMINDER VIDEO" + addMonths(date, video.get(plan.getPlanName()).getValidity());
		} catch (ParseException e) {
			throw new AddSubscriptionFailed("ADD_SUBSCRIPTION_FAILED", " : INVALID_DATE");
		}

	}

	public String addPodCastSub(AddSubscription plan, TopUp topUp, String date) {

		isValidPlan(plan.getPlanName());

		try {
			return "RENEWAL_REMINDER PODCAST " + addMonths(date, podcast.get(plan.getPlanName()).getValidity());
		} catch (ParseException e) {
			throw new AddSubscriptionFailed("ADD_SUBSCRIPTION_FAILED", " : INVALID_DATE");
		}

	}

	public String addMonths(String dateAsString, int nbMonths) throws ParseException {

		Date dateAsObj = dateFormat.parse(dateAsString);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateAsObj);
		cal.add(Calendar.MONTH, nbMonths);
		Date dateAsObjAfterAMonth = cal.getTime();
		return dateFormat.format(dateAsObjAfterAMonth);

	}

	public void validateJson(Subscriptions data) {

		if (data.getPlans().size() <= 0) {
			throw new AddSubscriptionFailed("SUBSCRIPTIONS_NOT_FOUND", null);
		}

		HashSet<String> set = new HashSet<>();

		data.getPlans().stream().forEach((plan) -> {
			if (set.contains(plan.getSubscriptionCatogary())) {
				throw new AddSubscriptionFailed("ADD_SUBSCRIPTION_FAILED", " : DUPLICATE_CATEGORY");
			} else {
				set.add(plan.getSubscriptionCatogary());
			}
		});

		if (data.getTopUp().size() > 1) {
			throw new AddSubscriptionFailed("ADD_TOPUP_FAILED", " : DUPLICATE_TOPUP");
		}

	}

	@PostConstruct
	public void initialiaze() {
		music.put("free", new Plans("free", 0, 1));
		music.put("personal", new Plans("persnal", 100, 1));
		music.put("premium", new Plans("premium", 250, 3));

		video.put("free", new Plans("free", 0, 1));
		video.put("personal", new Plans("persnal", 200, 1));
		video.put("premium", new Plans("premium", 500, 3));

		podcast.put("free", new Plans("free", 0, 1));
		podcast.put("personal", new Plans("persnal", 100, 1));
		podcast.put("premium", new Plans("premium", 300, 3));
	}

}
