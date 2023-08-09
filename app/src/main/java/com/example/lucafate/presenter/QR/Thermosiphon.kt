package com.example.lucafate.presenter.QR

import javax.inject.Inject

class Thermosiphon : Pump() {
    private val heater = Heater()

    @Inject
    Thermosiphon(Heater heater) {
        this.heater = heater;
    }

}