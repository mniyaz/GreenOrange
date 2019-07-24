package com.example.vechicletracker.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vechicletracker.Activity.MainActivity;
import com.example.vechicletracker.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class TrackingFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private SupportMapFragment mMapFragment;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 4;
    private GoogleMap googleMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Boolean mRequestingLocationUpdates;
    boolean isGPSEnabled = false;
    private AlertDialog.Builder alertDialog;
    private DialogInterface dismissDialog;
    private boolean isGetLocationInfo;
    private boolean Storelocation = false;
    private GoogleMap mGoogleMap;
    private Marker myMarker;
    private Location mCurrentLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLastLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.tracking_map, container, false);

        if (googleMap == null) {
            mMapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
            mMapFragment.getMapAsync(this);
        } else {
            GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());
        }
        mRequestingLocationUpdates = false;
        initGoogleApiClient();
        createLocationRequest();
        startUpdatesButtonHandler();

        return rootView;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission((Activity)getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (mCurrentLocation == null) {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mCurrentLocation = location;
                        mLastLocation = location;
                    }
                }
            });
            //updateUI();
            // getInitialService(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
        }

        if (mRequestingLocationUpdates) {
            startLocationUpdates();
            checkLocationEnable();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mFusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                    if (location != null) {
                        mLastLocation = location;
                    }
                }
        });

        float bitmapWidth = getResources().getDimension(R.dimen.bitmapWidth);
        float bitmapHeight = getResources().getDimension(R.dimen.bitmapHeight);

        {
            if ( mGoogleMap!= null) {
                mGoogleMap.clear();
                Bitmap smallMarker = null;
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_navigation_icon_moving);
                Bitmap b = bitmapdraw.getBitmap();
                smallMarker = Bitmap.createScaledBitmap(b, Math.round(bitmapWidth), Math.round(bitmapHeight), false);
                myMarker = mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()))
                        .title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()), 14.0f));
            }
        }
         mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
               // int position = (int)(marker.getTag());
                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(R.layout.bottom_sheet_dialog);
                dialog.show();
                //Using position get Value from arraylist
                return false;
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.clear();
        if (mGoogleMap != null) {
            mGoogleMap.clear();
        }

        mGoogleMap = googleMap;

    }
    private void initGoogleApiClient() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    private void startUpdatesButtonHandler() {

        if (!isPlayServicesAvailable(getActivity())) return;
        if (!mRequestingLocationUpdates) {
            mRequestingLocationUpdates = true;
        } else {
            return;
        }


        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            checkLocationEnable();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                showRationaleDialog();
            } else {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        }
    }

    public static boolean isPlayServicesAvailable(Context context) {

        int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance().getErrorDialog((Activity) context, resultCode, 2).show();
            return false;
        }
        return true;
    }

    void checkLocationEnable() {

        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!isGPSEnabled) {
            isGPSEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled) {
                showSettingsAlert(getActivity());
                isGetLocationInfo = false;
            } else {
                startLocationUpdates();
            }
        } else {
            startLocationUpdates();
        }

    }
    public void showSettingsAlert(final Activity activity) {

        alertDialog = new android.app.AlertDialog.Builder(
                activity);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dismissDialog = dialog;
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        activity.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dismissDialog = dialog;
                        dialog.cancel();

                    }
                });
        alertDialog.show();

    }
    private void startLocationUpdates() {


        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        } else {
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    checkLocationEnable();
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                        mRequestingLocationUpdates = false;
                        Toast.makeText(getActivity(), "To enable the function of this application please enable location permission of the application from the setting screen of the terminal.", Toast.LENGTH_SHORT).show();
                    } else {
                        showRationaleDialog();
                    }
                }
                break;
            }
        }
    }
    private void showRationaleDialog() {
        new android.app.AlertDialog.Builder(getActivity())
                .setPositiveButton("To give permission", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                    }
                })
                .setNegativeButton("do not do", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Position information permission was not allowed.", Toast.LENGTH_SHORT).show();
                        mRequestingLocationUpdates = false;
                    }
                })
                .setCancelable(false)
                .setMessage("This application needs to allow use of location information.")
                .show();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        isPlayServicesAvailable(getActivity());

    }

    @Override
    public void onPause() {
        super.onPause();
        Storelocation = false;
        // Stop location updates to save battery, but don't disconnect the GoogleApiClient object.
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    @Override
    public void onStop() {
        stopLocationUpdates();
        if (this.mGoogleApiClient != null) {
            this.mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dismissDialog != null) {
            dismissDialog = null;
            dismissDialog.cancel();
        }
    }
    protected void stopLocationUpdates() {


        if (mGoogleApiClient.isConnected())
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (com.google.android.gms.location.LocationListener) this);
    }



}

