package com.gildedrose;

import com.gildedrose.updater.QualityUpdater;

class GildedRose {

	private final QualityUpdater[] updaters;

	public GildedRose(QualityUpdater[] updaters) {
		this.updaters = updaters;
	}

	public void updateQuality() {
		for (final QualityUpdater updater : updaters) {
			updater.updateQuality();
		}
	}
}
