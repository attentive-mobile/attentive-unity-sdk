
using UnityEngine;
using System.Runtime.InteropServices;
using System.Collections.Generic;
using Attentive.Unity.Android;


namespace Attentive.Unity
{
    public class AttentiveUnitySDK
    {

        private AttentiveAndroidBridge androidBridge;


        public AttentiveUnitySDK(string domain, string mode)
        {
#if UNITY_ANDROID && !UNITY_EDITOR
                androidBridge = new AttentiveAndroidBridge(domain, mode);
#endif
        }

        public void Trigger()
        {
#if UNITY_ANDROID && !UNITY_EDITOR
                androidBridge.Trigger();
#endif
        }

        public void ClearCookies()
        {
#if UNITY_ANDROID && !UNITY_EDITOR
                androidBridge.ClearCookies();
#endif
        }
}