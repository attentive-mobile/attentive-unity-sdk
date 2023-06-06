using UnityEngine;
using System.Runtime.InteropServices;
using System.Collections.Generic;
using System.Linq;
using Attentive.Unity.Models;


namespace Attentive.Unity.Android
{
    public class AttentiveAndroidBridge
    {

#if UNITY_ANDROID && !UNITY_EDITOR

        private AndroidJavaObject attentiveAndroidWrapper;
        private AndroidJavaObject playerActivity;

        public AttentiveAndroidBridge(string domain, string mode) {
            AndroidJavaClass unityPlayerClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer"); 
            playerActivity = unityPlayerClass.GetStatic<AndroidJavaObject>("currentActivity");
            attentiveAndroidWrapper = new AndroidJavaObject("com.attentive.android.unitywrapper.AttentiveAndroidUnityWrapper");
            attentiveAndroidWrapper.Call("init", new object[3]{domain, mode, playerActivity});
        }

        public void Trigger() {
            attentiveAndroidWrapper.Call("trigger");
        }

        public void ClearCookies() {
            attentiveAndroidWrapper.Call("clearCookies");
        }

#endif

    }
}
