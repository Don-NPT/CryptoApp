/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.cryptoinfo.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.cryptoinfo.network.Coins
import com.example.android.cryptoinfo.network.CryptoApi
import com.example.android.cryptoinfo.network.CryptoInfo
import kotlinx.coroutines.launch

enum class CryptoApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<CryptoApiStatus>()
    private val _coins = MutableLiveData<List<Coins>>()

    val status: LiveData<CryptoApiStatus> = _status
    val coins: LiveData<List<Coins>> = _coins


    init {
        getCoinPhotos()
    }

    /**
     * Gets information from the API Retrofit service and updates the
     * [CryptoInfo] [List] [LiveData].
     */
    private fun getCoinPhotos() {
        viewModelScope.launch {
            _status.value = CryptoApiStatus.LOADING
            try {
                _coins.value = CryptoApi.RETROFIT_SERVICES.getCryptos().data.coins
                _status.value = CryptoApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CryptoApiStatus.ERROR
                _coins.value = listOf()
            }
        }
    }
}
