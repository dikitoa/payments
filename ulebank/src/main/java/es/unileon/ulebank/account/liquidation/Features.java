package es.unileon.ulebank.account.liquidation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unileon.ulebank.account.Account;

public class Features<T> {

	private List<AbstractFeatureExtractor<T>> featureExtractors;

	public Features() {
		this.featureExtractors = new ArrayList<AbstractFeatureExtractor<T>>();
	}

	public boolean addFeature(AbstractFeatureExtractor<T> featureExtractor) {
		return this.featureExtractors.add(featureExtractor);
	}

	public Map<String, T> getFeatures() {
		Map<String, T> features = new HashMap<String, T>();
		for (AbstractFeatureExtractor<T> feature : featureExtractors) {
			features.put(feature.getFeatureName().replace(" ", ""), feature.getFeature());
		}
		return features;
	}

	public Map<String, T> generateRandomFeatures() {
		Map<String, T> features = new HashMap<String, T>();
		for (AbstractFeatureExtractor<T> feature : featureExtractors) {
			features.put(feature.getFeatureName().replace(" ", ""),
					feature.generateRandomFeature());
		}
		return features;
	}

	public String[] getFeaturesNames() {
		String[] names = new String[this.featureExtractors.size()];
		int i = 0;
		for (AbstractFeatureExtractor<T> feature : featureExtractors) {
			names[i++] = feature.getFeatureName();
		}
		return names;
	}

	void updateFeatures(Account account, Date min, Date max) {
		for (AbstractFeatureExtractor<T> feature : featureExtractors) {
			feature.updateFeature(account, min, max);
		}
	}
}