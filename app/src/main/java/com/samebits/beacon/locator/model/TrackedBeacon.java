/*
 *
 *  Copyright (c) 2015 SameBits UG. All rights reserved.
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

package com.samebits.beacon.locator.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.samebits.beacon.locator.util.BeaconUtil;

/**
 * Created by vitas on 20/12/15.
 */
public class TrackedBeacon implements IManagedBeacon, Parcelable {
    public static final Creator<TrackedBeacon> CREATOR = new Creator<TrackedBeacon>() {
        @Override
        public TrackedBeacon createFromParcel(Parcel in) {
            return new TrackedBeacon(in);
        }

        @Override
        public TrackedBeacon[] newArray(int size) {
            return new TrackedBeacon[size];
        }
    };
    private String id;
    private String uuid = "";
    private long lastSeenTime;
    private String major = "";
    private String minor = "";
    private int txPower;
    private int rssi;
    private double distance;
    private String bleName;
    private String bleAddress;
    private int type;
    private String urlEddystone;
    private boolean tracked;

    public TrackedBeacon(DetectedBeacon detectedBeacon) {
        setId(detectedBeacon.getId());
        setTimeLastSeen(detectedBeacon.getTimeLastSeen());
        setBluetoothName(detectedBeacon.getBluetoothName());
        setBluetoothAddress(detectedBeacon.getBluetoothAddress());
        setUUID(detectedBeacon.getUUID());
        setRssi(detectedBeacon.getRssi());
        setTxPower(detectedBeacon.getTxPower());
        setType(detectedBeacon.getBeaconTypeCode());
        setUrl(detectedBeacon.getEddystoneURL());
        setDistance(detectedBeacon.getDistance());
        setMajor(detectedBeacon.getMajor());
        setMinor(detectedBeacon.getMinor());
    }

    public TrackedBeacon() {

    }

    protected TrackedBeacon(Parcel in) {
        id = in.readString();
        uuid = in.readString();
        lastSeenTime = in.readLong();
        major = in.readString();
        minor = in.readString();
        txPower = in.readInt();
        rssi = in.readInt();
        distance = in.readDouble();
        bleName = in.readString();
        bleAddress = in.readString();
        type = in.readInt();
        urlEddystone = in.readString();
        tracked = in.readByte() != 0;
    }

    @Override
    public BeaconType getBeaconType() {
        return getType() == -1 ? BeaconType.UNSPECIFIED : BeaconType.values()[getType()];
    }

    @Override
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String getEddystoneURL() {
        return urlEddystone;
    }

    @Override
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public long getTimeLastSeen() {
        return lastSeenTime;
    }

    public void setTimeLastSeen(long lastSeenTime) {
        this.lastSeenTime = lastSeenTime;
    }

    @Override
    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int getTxPower() {
        return txPower;
    }

    public void setTxPower(int txPower) {
        this.txPower = txPower;
    }

    @Override
    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    @Override
    public boolean equalTo(IManagedBeacon target) {
        return getId().equals(target);
    }

    @Override
    public String getBluetoothName() {
        return bleName;
    }

    public void setBluetoothName(String bleName) {
        this.bleName = bleName;
    }

    @Override
    public String getBluetoothAddress() {
        return bleAddress;
    }

    public void setBluetoothAddress(String bleAddress) {
        this.bleAddress = bleAddress;
    }

    @Override
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.urlEddystone = url;
    }

    public boolean isTracked() {
        return tracked;
    }

    public void setTracked(boolean tracked) {
        this.tracked = tracked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(uuid);
        dest.writeLong(lastSeenTime);
        dest.writeString(major);
        dest.writeString(minor);
        dest.writeInt(txPower);
        dest.writeInt(rssi);
        dest.writeDouble(distance);
        dest.writeString(bleName);
        dest.writeString(bleAddress);
        dest.writeInt(type);
        dest.writeString(urlEddystone);
        dest.writeByte((byte) (tracked ? 1 : 0));
    }

    @Override
    public boolean isEddyStoneTLM() {
        return getBeaconType() == BeaconType.EDDYSTONE_TLM;
    }

    @Override
    public boolean isEddyStoneUID() {
        return getBeaconType() == BeaconType.EDDYSTONE_UID;
    }

    @Override
    public boolean isEddyStoneURL() {
        return getBeaconType() == BeaconType.EDDYSTONE_URL;
    }

    @Override
    public boolean isEddystone() {
        return (getBeaconType() == BeaconType.EDDYSTONE_UID)
                || (getBeaconType() == BeaconType.EDDYSTONE_URL) || (getBeaconType() == BeaconType.EDDYSTONE_TLM);
    }


}
