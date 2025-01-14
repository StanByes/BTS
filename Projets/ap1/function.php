<?php
function formatDateForElement($date): string
{
    return $date->format("Y-m-d");
}

function formatTimeForElement($date, $second = true): string
{
    return $date->format("H:i" . ($second ? ":s" : ""));
}

function formatDate($date): string
{
    return $date->format("d/m/Y");
}

function formatTime($time): string
{
    return $time->format("H\hi");
}

function formatDateTime($datetime): string
{
    return $datetime->format("d/m/Y à H\hi");
}
