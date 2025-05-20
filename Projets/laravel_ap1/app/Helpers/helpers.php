<?php
if (!function_exists('formatDateForElement')) {
    function formatDateForElement($date): string
    {
        return $date->format("Y-m-d");
    }
}

if (!function_exists('formatTimeForElement')) {
    function formatTimeForElement($date, $second = true): string
    {
        return $date->format("H:i" . ($second ? ":s" : ""));
    }
}

if (!function_exists('formatDate')) {
    function formatDate($date): string
    {
        return $date->format("d/m/Y");
    }
}

if (!function_exists('formatTime')) {
    function formatTime($time): string
    {
        return $time->format("H\hi");
    }
}

if (!function_exists('formatDateTime')) {
    function formatDateTime($datetime): string
    {
        return $datetime->format("d/m/Y à H\hi");
    }
}
