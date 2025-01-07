<?php

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
