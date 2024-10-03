package com.example.classes.class_07.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.classes.R
import com.example.classes.databinding.Class07HomeFragmentBinding


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var binding: Class07HomeFragmentBinding
    private val channel_id = "idChanel"
    private val channe_name = "nameChanel"
    private val notification_id = 0

    private val camera_permission_launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { is_granted ->
            if (is_granted)
                open_camera()
            else
                Toast.makeText(context, "You need to accept the camera permission", Toast.LENGTH_SHORT).show()
        }

    private val notifications_permission_launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { is_granted ->
            if (is_granted)
                create_notification()
            else
                Toast.makeText(context, "You need to accept the notification permission", Toast.LENGTH_SHORT)
                    .show()
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = Class07HomeFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler()
        camera_permission()
        notifications_permission()
        launch_notification()
    }

    private fun recycler() {
        binding.btnRecyclerFragment.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recyclerFragment)
        }
    }

    private fun camera_permission() {
        binding.btnCameraPermission.setOnClickListener {
            ask_camera_permission()
        }
    }
    
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun notifications_permission() {
        binding.btnNotificationsPermission.setOnClickListener {
            ask_notification_permission()
        }
    }

    private fun open_camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 0)
    }

    private fun ask_camera_permission() {
        when {
            // When the permit is already granted
            ContextCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                open_camera()
            }

            // When permission is requested and rejected
            shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA) -> {
                AlertDialog.Builder(context).setTitle("Camera permission")
                    .setMessage("Accept the permission").setPositiveButton("Yes") { _, _ ->
                        camera_permission_launcher.launch(android.Manifest.permission.CAMERA)
                    }.setNegativeButton("No") { _, _ -> }.show()
            }

            // When entering the camera for the first time
            else -> camera_permission_launcher.launch(android.Manifest.permission.CAMERA)
        }
    }
    
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun ask_notification_permission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                create_notification()
            }
            
            shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS) -> {
                AlertDialog.Builder(context).setTitle("Notification permission")
                    .setMessage("Accept the permission").setPositiveButton("Yes") { _, _ ->
                        notifications_permission_launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                    }.setNegativeButton("No") { _, _ -> }.show()
            }
            
            else -> notifications_permission_launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun launch_notification() {
        binding.btnLaunchNotification.setOnClickListener {
            create_notification()
        }
    }

    private fun create_notification_channel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channel_id, channe_name, importance)
            val notification_manager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notification_manager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    private fun create_notification() {
        // Create a notification channel (only required for Android Oreo and higher)
        create_notification_channel()

        // Build the notification
        val notification = NotificationCompat.Builder(requireContext(), channel_id)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Notification title")
            .setContentText("this is the notification text")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true) // This causes the notification to close when the user clicks on it

        // Show the notification
        NotificationManagerCompat.from(requireContext())
            .notify(notification_id, notification.build())
    }

}