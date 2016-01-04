/*
 *
 *  Copyright (c) 2016 SameBits UG. All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.samebits.beacon.locator.action;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;

import com.samebits.beacon.locator.model.NotificationAction;

/**
 * Created by vitas on 03/01/16.
 */
public class AlarmOnAction extends NoneAction {


    public AlarmOnAction(String param, NotificationAction notification) {
        super(param, notification);
    }

    @Override
    public void execute(Context context) {

        Intent newIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
        if (!isParamEmpty()) {
            newIntent.putExtra(AlarmClock.EXTRA_MESSAGE, this.param);
        }
        context.startActivity(newIntent);
        super.execute(context);
    }

    @Override
    public String toString() {
        return "AlarmOnAction, param: "+param;
    }
}
